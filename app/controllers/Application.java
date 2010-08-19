package controllers;

import java.util.*;
import play.mvc.*;
import play.data.validation.*;
import models.*;

public class Application extends Controller {
    
    public static void index() {
        List<Snippet> snippets = Snippet.findAll();
        render(snippets);
    }
    
    public static void add(@Valid Snippet snippet){

      //We don't want to override existing snippets
      snippet.id = null;
      snippet.score = 0;
      snippet.ident = snippet.ident.trim();

      if(Snippet.exists(snippet.ident))
        Validation.addError("snippet.ident", "This gist already exists");

      if(!validation.hasErrors()) {
        snippet.save();
      }
      else{
        params.flash();
        validation.keep();
      }
      
      index();
    }
    
    public static void preview(String ident){
      render(ident);
    }
}