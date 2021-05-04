package entities.renameme;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "RenameMe.deleteAllRows", query = "DELETE from RenameMe")
public class RenameMe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dummyStr1;
    private String dummyStr2;

    public RenameMe() {
    }

    public RenameMe(String dummyStr1, String dummyStr2) {
        this.dummyStr1 = dummyStr1;
        this.dummyStr2 = dummyStr2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDummyStr1() {
        return dummyStr1;
    }

    public void setDummyStr1(String dummyStr1) {
        this.dummyStr1 = dummyStr1;
    }

    public String getDummyStr2() {
        return dummyStr2;
    }

    public void setDummyStr2(String dummyStr2) {
        this.dummyStr2 = dummyStr2;
    }
    
    
    

   
}
