package models;

import play.*;
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
  @Column(length=20)
  public String ident;
  @Column(length=200)
  public String description;
  public int score = 0;

  public static boolean exists(String ident){
    return find("ident=?", ident).first() != null;
  }
}