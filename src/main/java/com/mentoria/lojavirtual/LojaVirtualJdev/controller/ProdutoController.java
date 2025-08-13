package com.mentoria.lojavirtual.LojaVirtualJdev.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mentoria.lojavirtual.LojaVirtualJdev.ExceptionMentoriaJava;
import com.mentoria.lojavirtual.LojaVirtualJdev.model.Produto;
import com.mentoria.lojavirtual.LojaVirtualJdev.repository.ProdutoRepository;
import com.mentoria.lojavirtual.LojaVirtualJdev.service.ServiceSendEmail;

@RestController
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ServiceSendEmail serviceSendEmail;

	@PostMapping(value = "/salvarProduto")
	public ResponseEntity<Produto> salvarProduto(@Valid @RequestBody Produto produto)
			throws ExceptionMentoriaJava, MessagingException, IOException {

		if (produto.getEmpresa() == null || produto.getEmpresa().getId_pessoa() == null) {
			throw new ExceptionMentoriaJava("Empresa deve ser informada!");
		}
		
		if(produto.getNome().length() < 10) {
			throw new ExceptionMentoriaJava("O nome do produto deve ter no MÍNIMO 10 caracteres!");

		}
		
		if (produto.getTipo_unidade() == null || produto.getTipo_unidade().trim().isEmpty()) {
			throw new ExceptionMentoriaJava("Tipo da unidade deve ser informada!");
		}

		if (produto.getId_produto() == null) {

			List<Produto> produtos = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(),
					produto.getEmpresa().getId_pessoa());

			if (!produtos.isEmpty()) {
				throw new ExceptionMentoriaJava("Não pode cadastrar PRODUTO com mesmo nome!");
			}
		}

		if (produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId_categ_prod() == null) {
			throw new ExceptionMentoriaJava("Categoria deve ser informada!");
		}

		if (produto.getMarcaProduto() == null || produto.getMarcaProduto().getId_marca_prod() == null) {
			throw new ExceptionMentoriaJava("Marca deve ser informada!");

		}

		if (produto.getQtdEstoque() < 1) {
			throw new ExceptionMentoriaJava("Quantidade de estoque deve ser no mínimo 1!");
		}

		if (produto.getImagens() == null || produto.getImagens().isEmpty() || produto.getImagens().size() == 0) {
			throw new ExceptionMentoriaJava("Adicione imagens para o produto!");

		}
		if (produto.getImagens().size() < 3) {
			throw new ExceptionMentoriaJava("Informe pelo menos 3 imagens para o produto!");

		}
		if (produto.getImagens().size() > 6) {
			throw new ExceptionMentoriaJava("Informe no máximo 6 imagens para o produto!");

		}
		if (produto.getId_produto() == null) {

			for (int x = 0; x < produto.getImagens().size(); x++) {

				produto.getImagens().get(x).setProduto(produto);
				produto.getImagens().get(x).setEmpresa(produto.getEmpresa());

				String base64Image = "";

				if (produto.getImagens().get(x).getImagem_original().contains("data:image")) {
					base64Image = produto.getImagens().get(x).getImagem_original().split(",")[1];
				} else {
					base64Image = produto.getImagens().get(x).getImagem_original();
				}

				byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

				BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

				if (bufferedImage != null) {

					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					int largura = Integer.parseInt("800");
					int altura = Integer.parseInt("600");

					BufferedImage resizedImage = new BufferedImage(largura, altura, type);
					Graphics2D g = resizedImage.createGraphics();
					g.drawImage(bufferedImage, 0, 0, largura, altura, null);
					g.dispose();

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);

					String miniImgBase64 = "data:image/png;base64,"
							+ DatatypeConverter.printBase64Binary(baos.toByteArray());

					produto.getImagens().get(x).setImagem_miniatura(miniImgBase64);

					bufferedImage.flush();
					resizedImage.flush();
					baos.flush();
					baos.close();

				}

			}
		}

		Produto produtoSalvo = produtoRepository.save(produto);

		if (produto.getAlerta_qtd_estoque() && produto.getQtdEstoque() <= 1) {

			StringBuilder html = new StringBuilder();
			html.append("<h2").append("Produto: " + produto.getNome())
					.append(" com estoque baixo: " + produto.getQtdEstoque());
			html.append("<p> Id prod:").append(produto.getId_produto()).append("</p>");

			if (produto.getEmpresa().getEmail() != null) {
				serviceSendEmail.enviarEmailHtml("Produto sem estoque", html.toString(),
						produto.getEmpresa().getEmail());
			}
		}

		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);

	}

	@DeleteMapping(value = "/deleteProduto/{id_produto}", produces = "application/text")
	public String delete(@PathVariable("id_produto") Long id_produto) {

		produtoRepository.deleteById(id_produto);

		return "ok";
	}

	@GetMapping(value = "/buscarProduto/{id_produto}")
	public ResponseEntity<Produto> buscarProduto(@PathVariable("id_produto") Long id_produto)
			throws ExceptionMentoriaJava {

		Produto produto = produtoRepository.findById(id_produto).orElse(null);

		if (produto == null) {
			throw new ExceptionMentoriaJava("Não encontrou produto com código: " + id_produto);
		}

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	/*
	 * @GetMapping(value = "buscarProdNome/{desc}") public
	 * ResponseEntity<List<Produto>> buscarProdNome(@PathVariable("desc") String
	 * desc){
	 * 
	 * List<Produto> produto =
	 * produtoRepository.buscarProdutoName(desc.toUpperCase());
	 * 
	 * return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK); }
	 */

}
