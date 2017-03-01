package pl.lodz.p.it.naspontanaapp.domain;

/**
 * Created by 'Jakub Dziworski' on 10.12.16
 */
public class CategoryOutputDto {
	
    Long id;
    
    String name;
    
    String verb;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVerb() {
        return this.verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        CategoryOutputDto that = (CategoryOutputDto) o;

        if (this.id != null ? !this.id.equals(that.id) : that.id != null) return false;
        if (this.name != null ? !this.name.equals(that.name) : that.name != null) return false;
        return this.verb != null ? this.verb.equals(that.verb) : that.verb == null;

    }

    @Override
    public int hashCode() {
        int result = this.id != null ? this.id.hashCode() : 0;
        result = 31 * result + (this.name != null ? this.name.hashCode() : 0);
        result = 31 * result + (this.verb != null ? this.verb.hashCode() : 0);
        return result;
    }
}
