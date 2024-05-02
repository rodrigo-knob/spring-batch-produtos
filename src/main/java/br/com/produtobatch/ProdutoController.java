package br.com.produtobatch;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        produtoService.uploadProdutoFile(file);
        return "Processamento iniciado";
    }

    @GetMapping("/processar")
    public String processar() {
        produtoService.processarProdutosAgendados();
        return "Processamento iniciado";
    }
}
