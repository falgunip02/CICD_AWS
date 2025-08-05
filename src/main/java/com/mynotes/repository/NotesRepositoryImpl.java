package com.mynotes.repository;

import com.mynotes.model.Note;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotesRepositoryImpl implements NotesRepository {
    List<Note> notes = new ArrayList<>();

    @PostConstruct
    void init(){
        Note note1 = new Note(1,"ReactJS", "Learn ReactJS for building user interfaces");
        Note note2 = new Note(2,"Spring Boot", "Learn Spring Boot for building backend applications");
        Note note3 = new Note(3,"Java", "Learn Java for building robust applications");
        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
    }

    @Override
    public Note saveNote(Note note) {
        notes.add(note);
        return note;
    }

    @Override
    public Note getNoteById(int id) {
        return notes.stream().filter(i -> i.getId() == id).findFirst().get();
    }

    @Override
    public List<Note> getAllNotes() {
        return notes;
    }

    @Override
    public void deleteNode(int id) {
        notes.removeIf(i -> i.getId() == id);
    }

    @Override
    public List<Note> getNotesByTitle(String title){
        List<Note> existingNotes = notes.stream().filter(n-> n.getTitle().toLowerCase().contains(title.toLowerCase())).toList();
        return existingNotes;
    }
}
