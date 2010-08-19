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
      //TODO: check if exist
      if(!validation.hasErrors())
          snippet.save();
      index();
    }
    
    public static void preview(String ident){
      render(ident);
    }
}