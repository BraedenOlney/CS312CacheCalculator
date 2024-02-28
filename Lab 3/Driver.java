//Braeden Olney
//I pledge that I have neither given nor received help from anyone other than the instructor/TA for all program components included here

//util for scanner
import java.util.*;



public class Driver {
    public static void main(String[] args) {
        //initialize scanner
        Scanner scan = new Scanner(System.in);

        //initialize a for A * 2^E
            int a;
        //initialize e for A * 2^E
            int e;
        //initialize cacheBlock var
            int cacheBlock;
        //initialize bytes in cache block var
            int bytesInCacheBlock;
        //initialize k for set association
            int k;
        //initialize memoryAddress in base 16 var
            String memoryAddress16 = "";
        //initialize memory address in base 16 to base 10 var
            int memoryAddress16Int = 0;
            //print prompts
            System.out.println("Main memory representation is A x 2^E");
            System.out.print("Value A: ");
            //save input as a
            a = scan.nextInt();
            //print prompt
            System.out.print("Exponent E: ");
            //save input as e
            e = scan.nextInt();
            //convert a and e to the memory bytes
            int bytes = (int) (a * (Math.pow(2, e)));
        //print prompt
            System.out.println("Main memory: " + a + " x 2^" + e + " =" + bytes);
            System.out.print("Cache blocks: ");
            //save cacheBlocks
            cacheBlock = scan.nextInt();
            //check if cacheBlock is a base 2 number
            base2(cacheBlock);
        //print prompt
            System.out.print("Bytes in Cache blocks: ");
            //save bytes in cacheblock
            bytesInCacheBlock = scan.nextInt();
            //check if its a base 2 number
            base2(bytesInCacheBlock);
            ////print prompt
            System.out.print("k-set associative value: ");
            //save as k
            k = scan.nextInt();
            //check if its a base 2 number
            base2(k);

            ////print prompt
            System.out.print("Memory address in Base 16: ");
            //save base 16 number
            memoryAddress16 = scan.next();

            //see if the input is a base 16 number
            //if not catch exception print statement and terminate program
            try {
                memoryAddress16Int = Integer.decode(memoryAddress16);
            }catch(Exception e1){
                System.out.println("Memory address is not in Base 16. Program terminated");
            }

            //check if the memory address is greater than the memory size
        //if so print statement and exit
            if(memoryAddress16Int > bytes){
                System.out.println("Size of address is larger than main memory size. Program terminated");
                System.exit(0);
            }
            //make address a binary number
            String AddressInBinary = Integer.toBinaryString(memoryAddress16Int);
            //call the addZero method to make the binary number have the correct number of bits
            AddressInBinary = addZeros(AddressInBinary);
            //print binary number
            System.out.println("Address in binary: " + AddressInBinary);
            //line break
            System.out.println("");


            //set line to the base 2 power of cacheBlock
            int line = countBase2(cacheBlock);
            //set word to teg base 2 power of bytes in cacheBlock
            int word = countBase2(bytesInCacheBlock);
            //set tag to the difference in base 2 power of bytes of memory and the sum of line and word
            int tag = countBase2(bytes) - (line + word);
            //print out the statements and line word and tag with the assocated binary numbers
            System.out.println("Direct Cache mapping of " + memoryAddress16 + " address");
            System.out.print("[TAG] " + tag + " : [LINE] " + line + ": [WORD] " + word + "\n");
            System.out.print("[TAG] " + AddressInBinary.substring(0,tag) + " : [LINE] " +
                    AddressInBinary.substring(tag,line+tag) + ": [WORD] " +
                    AddressInBinary.substring(line+tag) + "\n");

            //set word to the base 2 power of bytes in cache
            word = countBase2(bytesInCacheBlock);
            //set tag to the difference of base 2 power of bytes of memory and word
            tag = countBase2(bytes) - word;
        //print out the statements and word and tag with the assocated binary numbers
            System.out.println("Associative Cache mapping of " + memoryAddress16 + " address");
            System.out.println("[TAG] " + tag + " : [WORD] " + word);
            System.out.println("[TAG] " + AddressInBinary.substring(0,tag) + " : [WORD] " +
                    AddressInBinary.substring(tag));

            //set set to the base 2 power of cacheBlock divided by the set k
            int set = countBase2(cacheBlock / k);
            //set word to the base 2 pwoer of bytes in cache block
            word = countBase2(bytesInCacheBlock);
            //set tag to the base 2 power of bytes in memory minus the sum of set and word
            tag = countBase2(bytes) - (set + word);
        //print out the statements and line word and tag with the assocated binary numbers
            System.out.println("4-way Cache mapping of " + memoryAddress16 + " address");
            System.out.print("[TAG] " + tag + " : [SET] " + set + ": [WORD] " + word + "\n");
            System.out.print("[TAG] " + AddressInBinary.substring(0,tag) + " : [SET] " +
                    AddressInBinary.substring(tag,set+tag) + ": [WORD] " +
                    AddressInBinary.substring(set+tag) + "\n");
        }


    //add zero method adds leading zeros to a binary string

    public static String addZeros(String a){
        //gets the size of the string
        int aSize = a.length();
        //check how many zeros the string needs and adds them through string concatantion
        if(aSize % 4 == 1){
            a = "000" + a;
        }else if(aSize % 4 == 2){
            a = "00" + a;
        }else{
            a = "0" + a;
        }
        //returns the string
        return a;
    }
    //base2 checks if a string is in base 2 and if not prints error and exits
    public static void base2(int a){
        while(a!=2){
            a = a/2;
            if(a%2 == 1){
                System.out.println("Cache Blocks is not in Base 2. Program terminated");
                System.exit(0);
            }
        }
    }

    //countbase2 gets the exponent x of the number in 2^x
    public static int countBase2(int a){
        //start counter at 2 because the while stops at 2
        int counter = 1;
        //while the number is greater than 2
        while(a!=2){
            //increment the counter and half the int
            counter++;
            a = a/2;
        }
        //after while return the counter
        return counter;
    }

}
