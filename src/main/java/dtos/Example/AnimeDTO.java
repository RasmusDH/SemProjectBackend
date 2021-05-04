/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos.Example;

/**
 *
 * @author peter
 */
public class AnimeDTO {
    
    String anime;
    String quote;
    String character;

    public AnimeDTO(String anime, String quote, String character) {
        this.anime = anime;
        this.quote = quote;
        this.character = character;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
    
    
    
}
