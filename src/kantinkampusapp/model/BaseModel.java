package kantinkampusapp.model;

/**
 * Abstract class sebagai base untuk semua model.
 * BAB 4: Abstract Class & Interface
 */
public abstract class BaseModel {
    protected int id;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Abstract method wajib diimplementasi subclass
    public abstract String toDisplayString();
}
