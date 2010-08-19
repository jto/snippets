package models;

import play.db.jpa.*;
import play.data.validation.*;

import javax.persistence.*;

@Entity
public class Snippet extends Model{
  
  public Snippet(){
    super();
  }
  
  public Snippet(String ident){
    super();
    this.ident = ident;
  }
  
  @Required
  public String ident;
  public String description;
  public int score = 0;
}