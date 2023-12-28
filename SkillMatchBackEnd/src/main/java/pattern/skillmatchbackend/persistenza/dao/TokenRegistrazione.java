package pattern.skillmatchbackend.persistenza.dao;

import org.apache.el.parser.Token;

public interface TokenRegistrazione {

    public Token findByToken(String token);

    public void save(Token token);

    public void delete(Token token);

    public Token finByUser(String username);
}
