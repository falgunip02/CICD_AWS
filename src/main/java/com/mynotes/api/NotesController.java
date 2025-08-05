package com.mynotes.api;

import com.mynotes.exception.DuplicateRecordException;
import com.mynotes.model.Note;
import com.mynotes.repository.NotesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    private NotesRepository repo;

    public NotesController(NotesRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note){
        List<Note> notes = new ArrayList<>();
        notes = getAllNotes();
        for(Note n: notes){
            if(n.getId() == note.getId()){
                // throw exception DuplicateRecordException
                throw new DuplicateRecordException("Note with this id already exist");
            }
        }
        return repo.saveNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes(){
        return repo.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note findNote(@PathVariable("id") int id){
        return repo.getNoteById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable("id") int id){
        repo.deleteNode(id);
    }

//    @GetMapping("/title/{title}")
//    public List<Note> getNotesByTitle(@PathVariable("title") String title){
//        return repo.getNotesByTitle(title);
//    }

    @GetMapping("/search")
    public List<Note> getNotesByTitle(@RequestParam("title") String title){
        return repo.getNotesByTitle(title);
    }

//    @GetMapping("/{title}")
//    public void findNoteByTitle(@PathVariable("title") int title){
//        return repo.ge(title);
//    }

//    @GetMapping("/hello")
//    public String sayHello(){
//        return "Hello from Spring Boot";
//    }
//
//    @GetMapping("/note")
//    public Note getNote(){
//        Note note = new Note(23,"API","Learn Rest API");
//        return note;
//    }
}
