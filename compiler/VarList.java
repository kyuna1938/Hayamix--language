import java.util.ArrayList;


public class VarList {
    private ArrayList<VarInfo> varList;  
    private int nextAddress;         
    
    
    VarList() {
        varList = new ArrayList<VarInfo>();
        nextAddress = 0;
    }

    
    private VarInfo getVar (String name) {
        for (VarInfo var: varList) {
            if (name.equals (var.getName())) {
                return var;
            }
        }
        return null;
    }

    
    boolean exist (String name) {
        VarInfo var = getVar (name);
        if (var != null) return true;
        else return false;
    }

    
    boolean registerNewVariable (TypeKind TypeKind, String name, int size) {
        if (!exist (name)) {
            varList.add (new VarInfo (TypeKind,name, nextAddress, size));    
            nextAddress += size;    
            return true;            
        } else {
            return false;
        }
    }

    
    int getAddress (String name) {
        VarInfo var = getVar (name);
        if (var != null) return var.getAddress();
        else return -1;
    }

    
    TypeKind getTypeKind (String name) {
        VarInfo var = getVar (name);
        if (var != null) return var.getTypeKind();
        else return TypeKind.NULL;
    }

    
    boolean checkTypeKind (String name, TypeKind TypeKind) {
        VarInfo var = getVar (name);
        return (TypeKind.equals (var.getTypeKind()));
    }

    
    int getSize (String name) {
        VarInfo var;
        if ((var= getVar (name)) != null) return var.getSize();
        else return -1;
    }

    
    public static void main (String[] args) {
        VarList VarList = new VarList();

        for (int i=0; i< 4; ++i)
            VarList.registerNewVariable (TypeKind.INT, "var"+i, 1);
		VarList.registerNewVariable (TypeKind.ARRAYOFINT, "var4", 10);

        for (int i=0; i<5; ++i) {
            String name = "var" + i;
            if (VarList.checkTypeKind (name, TypeKind.INT)) {              
	            System.out.printf ("\n",
                    name, VarList.getTypeKind (name), VarList.getAddress (name), VarList.getSize (name));
            } else if (VarList.checkTypeKind (name, TypeKind.ARRAYOFINT)) { 
	            System.out.printf ("\n",
                    name, VarList.getTypeKind (name), VarList.getAddress (name), VarList.getSize (name));
            }
        }
    }
}
