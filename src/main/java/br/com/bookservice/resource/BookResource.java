package br.com.bookservice.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.bookservice.model.Book;
import br.com.bookservice.proxy.CambioProxy;
import br.com.bookservice.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookResource {

	@Autowired
	private Environment environment;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CambioProxy cambioProxy;

	@GetMapping(value = "/{id}/{currency}")
	public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {

		var port = environment.getProperty("local.server.port");
		var book = bookRepository.getById(id);

		if (book == null) {
			throw new RuntimeException("Book nnot found");
		}

		var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
		book.setEnvironment("Book PORT: " + port + "Cambio PORT" + cambio.getEnvironment());
		book.setPrice(cambio.getConvertedValue());
		return book;
	}
	
//	@GetMapping(value = "/{id}/{currency}")
//	public Book getBook(@PathVariable("id") Long id, @PathVariable("currency") String currency) {
//
//		var port = environment.getProperty("local.server.port");
//		var book = bookRepository.getById(id);
//
//		if (book == null) {
//			throw new RuntimeException("Book nnot found");
//		}
//		
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", book.getPrice().toString());
//		params.put("from", "USD");
//		params.put("to", currency);
//		
//		var response = new RestTemplate()
//		.getForEntity("http://localhost:8000/cambio-service/{amount}/{from}/{to}", Cambio.class, params);
//
//		var cambio = response.getBody();
//		book.setEnvironment(port);
//		book.setPrice(cambio.getConvertedValue());
//		return book;
//	}
}
