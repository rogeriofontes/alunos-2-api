package br.com.unipac.alunosapi.api.v1.resources;

import br.com.unipac.alunosapi.model.domain.Aluno;
import br.com.unipac.alunosapi.model.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/alunos")
public class AlunoResources {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Aluno>> lista() {
        List<Aluno> alunos = alunoService.list();

        if (!alunos.isEmpty()) {
            return ResponseEntity.ok(alunos);
        }

       return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Aluno> findById(@PathVariable("id") Long id) {
        Optional<Aluno> alunoEncontrado = alunoService.findById(id);

        if (alunoEncontrado.isPresent()) {
            return ResponseEntity.ok(alunoEncontrado.get());
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Aluno> editar(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        Aluno alunoRetornado = alunoService.editar(id, aluno);

        if (alunoRetornado != null) {
            return ResponseEntity.ok(alunoRetornado);
        }

       return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno) {
        Aluno alunoRetornado = alunoService.salvar(aluno);

        if (alunoRetornado == null) {
            ResponseEntity.noContent().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoRetornado.getId()).toUri();
        return ResponseEntity.created(uri).body(alunoRetornado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
