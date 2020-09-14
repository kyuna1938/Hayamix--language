import java.io.*;
import java.util.*;

class stackm{//stack machine
    static int now;
    static int adress;
    static int[] stackInt = new int[100];
    static int[] result = new int[100];
    static boolean saveflg = true;
    static int count = 0;
    static int linecount = 0;
    static int skip;
    static boolean ifflg = false;
    static String[] ope;
    public static void main(String[] args)throws Exception {
        try{
            BufferedReader in = new BufferedReader(new FileReader(new File(args[0])));
            String line;
            while((line = in.readLine()) != null){
               linecount++;
            }
            ope = new String[linecount];
            linecount = 0;
            in = new BufferedReader(new FileReader(new File(args[0])));
            while((line = in.readLine()) != null){
                ope[linecount] = line;
                linecount++;
            }
            for(int i = 0;i < ope.length;i++){
                String[] data = ope[i].split(" ");
                if(data.length == 2){
                    if(data[0].equals("PUSHI") && count == 0){
                        adress = Integer.parseInt(data[1]);
                        now = adress;
                        count++;
                    } else if(data[0].equals("PUSHI")){
                        stackInt[now] = Integer.parseInt(data[1]);
                        now++;
                        //System.out.println(stackInt[now-1] + ":" + (now-1));
                        count++;
                    }

                    if(data[0].equals("PUSH") && count == 0){
                        adress = 0;
                        now = adress;
                        stackInt[now] = result[Integer.parseInt(data[1])];
                        now++;
                        count++;
                        saveflg = false;
                    } else if(data[0].equals("PUSH")){
                        stackInt[now] = result[Integer.parseInt(data[1])];
                        now++;
                        count++;
                    }

                    if(data[0].equals("IF")){
                        skip = Integer.parseInt(data[1]);
                        if(stackInt[now-1] != 0){
                            count = 0;
                            saveflg = true;
                        } else {
                            ifflg = true;
                            i = skip - 1;
                            count = 0;
                        }
                    }

                    if(data[0].equals("JUMP")){
                        i = Integer.parseInt(data[1])-1;
                    }
                } else {
                    if(data[0].equals("MUL")){                       
                        stackInt[now-2] = mul(stackInt[now-1],stackInt[now-2]);
                        now -= 2;
                        now++;
                        count++;
                    }
                    if(data[0].equals("ADD")){   
                        stackInt[now-2] = add(stackInt[now-1],stackInt[now-2]);//stackInt[now++] = add(stackInt[now-1],stackInt[now-2]) is bad. why? 
                        now -= 2;
                        now++;
                        count++;
                    }
                    if(data[0].equals("SUB")){                        
                        stackInt[now-2] = sub(stackInt[now-1],stackInt[now-2]);
                        now -= 2;
                        now++;
                        count++;
                    }
                    if(data[0].equals("DIV")){                       
                        stackInt[now-2] = div(stackInt[now-1],stackInt[now-2]);
                        now -= 2;
                        now++;
                        count++;
                    }
                    if(data[0].equals("ASSGN")){
                        //System.out.println(adress);
                        result[adress] = stackInt[now-1];
                    }
                    if(data[0].equals("REMOVE")){
                        count = 0;
                        saveflg = true;
                    }
                    if(data[0].equals("HALT"))return;
                    if(data[0].equals("OUTPUT")){
                        if(saveflg){
                            result[adress] = stackInt[now-1];
                        }
                        System.out.println(stackInt[now-1]);
                        count = 0;
                        saveflg = true;
                    }
                    if(data[0].equals("INPUT")){
                        stackInt[now] = (new Scanner(System.in)).nextInt();;
                        now++;
                    }
                }
            }
        } catch (FileNotFoundException e){ 
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e){ 
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static int add(int a, int b) {
        return (a+b);
    }
    public static int sub(int a, int b) {
        return (b-a);
    }
    public static int mul(int a, int b) {
        return (a*b);
    }
    public static int div(int a, int b) {
        return b/a;
    }
}