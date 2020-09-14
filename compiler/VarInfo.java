
public class VarInfo {
    private TypeKind TypeKind; 
    private String name;
    private int address;
    private int size;
    

    VarInfo(TypeKind TypeKind, String name, int address, int size) {
        this.TypeKind = TypeKind;
        this.name = name;
        this.address = address;
        this.size = size;
    }


    public TypeKind getTypeKind() {
        return TypeKind;
    }

    public String getName() {
        return name;
    }

    
    public int getAddress() {
        return address;
    }

    
    public int getSize() {
        return size;
    }
}
