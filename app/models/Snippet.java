package models;

import play.*;
import play.libs.*;
import play.db.jpa.*;
import play.cache.*;
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
  public int score = 0;

  public static boolean exists(String ident){
    return find("ident=?", ident).first() != null;
  }
  
  public String getDescription(){
    String url = String.format("http://gist.github.com/api/v1/json/%s", ident);
    String desc = Cache.get(ident, String.class);
    if(desc == null){
      try{
        desc = WS.url(url).get().getJson().getAsJsonObject()
          .get("gists").getAsJsonArray().get(0).getAsJsonObject()
          .get("description").getAsString();
      }
      catch(Exception e){
        desc = "";
      }
      Cache.set(this.ident, desc);
    }
    return desc;
  }
}