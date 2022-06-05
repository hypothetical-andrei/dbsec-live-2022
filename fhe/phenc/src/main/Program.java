package main;

import java.math.BigInteger;

import paillier.Paillier;

public class Program {

	public static void main(String[] args) {
		try {
			Paillier enc = new Paillier(10);
			BigInteger a = new BigInteger("2");
			BigInteger b = new BigInteger("3");
			enc.generateKeys();
			BigInteger encA =  enc.encrypt(a);
			BigInteger encB =  enc.encrypt(b);
//			we send the encypted for to the processor
			BigInteger encResult = encA.multiply(encB);
			System.out.println(enc.decrypt(encResult));
			
            String[] votes = {"1", "0","1", "0","1", "0"};
            BigInteger tally = new BigInteger("0");
            BigInteger encTally = enc.encrypt(tally);
            for (String item : votes) {
                BigInteger vote = new BigInteger(item);
                tally = tally.add(vote);

                encTally  = encTally.multiply(enc.encrypt(vote));
//
            }
            System.out.println("tally");
            System.out.println(tally);
            System.out.println(enc.decrypt(encTally));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
