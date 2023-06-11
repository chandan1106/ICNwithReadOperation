/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

/**
 *
 * @author USER
 */


class Crypt 
{
	char table[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

	String encryption(String msg,int key)
	{
		
		System.out.println("Encryption");
		System.out.println("-------------");
		char ch[]=msg.toCharArray();
		char cip[]=new char[msg.length()];
		for(int i=0;i<ch.length;i++)
		{
			int pos=0;
				for(int j=0;j<table.length;j++)
				{
					if(table[j]==ch[i])
						{
						pos=j;
						break;
						}
				}
			int newpos=pos+key;
			
			if(newpos>25)
			{
			int p=0;
				for(int k=0;k<=newpos;k++,p++)
					{
						if(p>25)
						p=0;
					}
			cip[i]=table[p-1];
			}
			else
			cip[i]=table[newpos];

			}

			System.out.println("Plain Text-->"+msg);
			System.out.println("Key-->"+key);
			System.out.println("Cipher Text-->"+new String(cip));
			return (new String(cip));
		}
	
	String decryption(String msg,int key)
	{
		System.out.println("DECRYPTION");
		System.out.println("-------------------");
		System.out.println("Cipher Text-->"+msg);
		System.out.println("Key-->"+key);
		char cip[]=msg.toCharArray();
		char plain[]=new char[msg.length()];
		for(int i=0;i<cip.length;i++)
		{
			int pos=0;
			for(int j=0;j<table.length;j++)
			{
				if(table[j]==cip[i])
				{
					pos=j;
					break;
				}
			}

			int newpos=pos-key;
	

		if(newpos<0)
			{
			while(newpos<0)
				{
				newpos=newpos+26;
				}
			plain[i]=table[newpos];
			}
			else
				plain[i]=table[newpos];

		}
		System.out.println("Original Text-->"+new String(plain));
return (new String(plain));
	}

	
}
