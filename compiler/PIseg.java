import java.io.*;   
import java.util.*;


class PIseg {
    ArrayList<Asm> pIseg; 
    int pIsegPtr; 

    PIseg() {
        pIseg = new ArrayList<Asm>();
        pIsegPtr = 0;             
    }
    
   
    int setI (Ope opCode, int flag, int addr) {
         
         Asm inst = new Asm(opCode,flag,addr);

         pIseg.add(inst);
         ++pIsegPtr; 

         return pIsegPtr-1;
      }

    
    int appendCode(Ope opCode, int addr) {
        return setI(opCode, 0, addr);
    }

    
    int appendCode(Ope opCode) {
        return setI(opCode, 0, 0);
    }

    
    int getLastCodeAddress () {
        return pIsegPtr-1;
    }

    
    void dump() {
        for (int i = 0; i < pIsegPtr; i++) {
            System.out.print(i + ": ");
            System.out.println(pIseg.get(i).printAsm());
        }
    }

    
    void dump2file (String outputFileName) {
        PrintWriter outputFile = null;
        try {
            outputFile = new PrintWriter(
                             new BufferedWriter(
                                 new FileWriter(outputFileName)));
            for (int i = 0; i < pIsegPtr; i++)
                outputFile.println((pIseg.get(i)).printAsm());
        } catch(IOException exception) {
            System.out.println(exception);
        } finally {
            outputFile.close();
            System.exit(1);
        }
    }

    
    void replaceCode (int ptr, Ope opCode) {
        int oldReg = (pIseg.get(ptr)).getReg();
        int oldAddr = (pIseg.get(ptr)).getAddr();
        Asm inst = new Asm (opCode, oldReg, oldAddr);
        pIseg.remove (ptr);
        pIseg.add (ptr,inst);
    }

    
    void replaceCode (int ptr, int addrs) {
        int oldReg = pIseg.get(ptr).getReg();
        Ope oldOp = pIseg.get(ptr).getOpe();
        Asm inst = new Asm (oldOp, oldReg, addrs);
        pIseg.remove (ptr);
        pIseg.add (ptr, inst);
    }
    
    
    Asm getAsm (int ptr) {
       return pIseg.get (ptr);
    }

    
    Ope getOpe (int ptr) {
        return pIseg.get(ptr).getOpe();
    }

    
    int getOperand (int ptr) {
        return pIseg.get(ptr).getAddr();
    }

    
    void removeCode (int ptr) {
       pIseg.remove (ptr); 
       --pIsegPtr;           
    }

    
    void removeLastCode() {
        pIseg.remove (pIsegPtr-1);
        --pIsegPtr;           
    }

    
    boolean checkOpe (int ptr, Ope opCode) {
        return pIseg.get(ptr).equals (opCode);
    }

    
    boolean checkOpe (int ptr, String op) {
        return pIseg.get(ptr).equals (op);
    }    
}
