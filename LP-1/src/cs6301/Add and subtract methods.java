//    public static Num unsignedAdd(Num a,Num b)
//    {
//    	Iterator<Long> it1 = a.digits.iterator();;
//    	Iterator<Long> it2 = b.digits.iterator();;
//
//    	long carry = 0;
//    	Long temp1 = it1.next();
//    	Long temp2 = it2.next();
//
//    	LinkedList<Long> result = new LinkedList<Long>();
//    	String outStr = "";
//
//    	while(temp1!=null && temp2!=null){
//    		Long sum = temp1 + temp2 + carry;
//    		result.addLast(sum%BASE);
//    		carry = sum/BASE;
//
//    		try{
//    			temp1 = it1.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp1 = null;
//    		}
//
//    		try{
//    			temp2 = it2.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp2 = null;
//    		}
//    	}
//
//    	while(temp1!=null){
//    		Long sum = temp1 + carry;
//    		result.addLast(sum%BASE);
//    		carry = sum/BASE;
//
//    		try{
//    			temp1 = it1.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp1 = null;
//    		}
//    	}
//
//    	while(temp2!=null){
//    		Long sum = temp2 + carry;
//    		result.addLast(sum%BASE);
//    		carry = sum/BASE;
//
//    		try{
//    			temp2 = it2.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp2 = null;
//    		}
//    	}
//
//    	if(carry>0)
//    		result.addLast(carry);
//
//
//
//    	for(int i = result.size()-1;i>=0;i--)
//    		outStr += result.get(i);
//
//    //	System.out.println(outStr);
//    	return new Num(outStr);
//    }
//
//    public static Num unsignedSubtract(Num a,Num b){
//    	Iterator<Long> it1 = a.digits.iterator();;
//    	Iterator<Long> it2 = b.digits.iterator();;
//
//    	boolean carry = false;
//    	Long temp1 = it1.next();
//    	Long temp2 = it2.next();
//
//    	LinkedList<Long> result = new LinkedList<Long>();
//    	String outStr = "";
//
//    	while(temp1!=null && temp2!=null){
//    		if(carry){
//    			if(temp1 == 0)
//    				temp1 = (long)9;
//    			else{
//    				temp1 -= 1;
//    				carry = false;
//    			}
//    		}
//    		if(temp1<temp2){
//    			temp1 += BASE;
//    			carry = true;
//    		}
//    		long difference = temp1 - temp2;
//    		result.addLast(difference%BASE);
//
//    		try{
//    			temp1 = it1.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp1 = null;
//    		}
//
//    		try{
//    			temp2 = it2.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp2 = null;
//    		}
//    	}
//
//
//    	while(temp1 != null){
//    		if(carry){
//    			if(temp1 == 0)
//    				temp1 = (long)9;
//    			else{
//    				temp1 -= 1;
//    				carry = false;
//    			}
//    		}
//    		result.addLast(temp1);
//    		try{
//    			temp1 = it1.next();
//    		}
//    		catch(NoSuchElementException e){
//    			temp1 = null;
//    		}
//    	}
//
//    	for(int i=result.size()-1;i>=0;i--)
//    		outStr += result.get(i);
//
//    	//System.out.println(outStr);
//    	return new Num(outStr);
//    }
//
//    public static Num add(Num a ,Num b){
//
//    	boolean signA = a.getSign();
//    	boolean signB = b.getSign();
//    	boolean outSign;
//    	Num tempResult;
//    	Num a1,b1;
//
//       	a.setPositive();
//       	b.setPositive();
//
//       	int comp=a.compareTo(b);
//
//       	if(comp<0){
//       		a1=b;
//       		b1=a;
//       		if(signA!=signB)
//       			outSign=signB;
//       		else{
//       			if(signA)
//       				outSign=true;
//       			else
//       				outSign=false;
//       		}
//       	}
//       	else if(comp>0){
//       		a1=a;
//       		b1=b;
//       		if(signA!=signB)
//       			outSign=signA;
//       		else{
//       			if(signA)
//       				outSign=true;
//       			else
//       				outSign=false;
//       		}
//       	}
//       	else
//       	{
//       		a1=a;
//       		b1=b;
//       		if(signA!=signB)
//       			outSign=signA;
//       		else
//       			outSign=false;
//       	}
//
//       	if(signA!=signB){
//      		tempResult = unsignedSubtract(a1,b1);
//
//      	}
//
//       	else{
//
//       		tempResult = unsignedAdd(a1,b1);
//       	}
//  		//System.out.println(outSign);
//  		if(signA==true)
//  			a.setNegative();
//  		else
//  			a.setPositive();
//  		if(signB==true)
//  			b.setNegative();
//  		else
//  			b.setPositive();
//  		return tempResult;
//    }
//
//    public static Num subtract(Num a, Num b){
//
//    	boolean signA = a.getSign();
//    	boolean signB = b.getSign();
//    	Num tempResult;
//    	boolean outSign;
//    	Num a1,b1;
//
//       	a.setPositive();
//       	b.setPositive();
//
//       	int comp = a.compareTo(b);
//
//       	if(comp<0){
//       		a1=b;
//       		b1=a;
//       		if(signA!=signB)
//       			outSign=signA;
//       		else{
//       			if(signA)
//       				outSign=false;
//       			else
//       				outSign=true;
//       		}
//       	}
//       	else if(comp>0){
//       		a1=a;
//       		b1=b;
//       		if(signA!=signB)
//       			outSign=signA;
//       		else{
//       			if(signA)
//       				outSign=true;
//       			else
//       				outSign=false;
//       		}
//       	}
//       	else
//       	{
//       		a1=a;
//       		b1=b;
//       		if(signA!=signB)
//       			outSign=signA;
//       		else
//       			outSign=false;
//       	}
//
//       	if(signA!=signB){
//      		tempResult = unsignedAdd(a1,b1);
//
//      	}
//
//       	else{
//
//       		tempResult = unsignedSubtract(a1,b1);
//       	}
//  		//System.out.println(outSign);
//  		if(signA==true)
//  			a.setNegative();
//  		else
//  			a.setPositive();
//  		if(signB==true)
//  			b.setNegative();
//  		else
//  			b.setPositive();
//  		return tempResult;
//
//
//    }
//
//     public int compareTo(Num b){
//
//    	//compare negative and positive numbers
//
//    	if(this.getSign() != b.getSign())
//
//    		if(this.getSign()==true)
//
//    			return -1;
//
//    		else
//
//    			return 1;
//
//    	//Remove any leading zeros in a and b
//
//    	String s1 = this.toString();
//
//    	String s2 = b.toString();
//
//    	//take care of all leading zeros
//
//    	while ((s1.length() > 1) && (s1.charAt(0) == '0'))
//
//    		s1 = s1.substring(1);
//
//    	//take care of all leading zeros
//
//    	while ((s2.length() > 1) && (s2.charAt(0) == '0'))
//
//    		s2 = s2.substring(1);
//
//    	//see if the numbers have different sizes
//
//    	if(s1.length() > s2.length()){
//
//    		if(this.sign)
//
//    			return -1;
//
//    		else
//
//    			return 1;
//
//    	}else if(s1.length() < s2.length()){
//
//    		if(this.sign)
//
//    			return 1;
//
//    		else
//
//    			return -1;
//
//    	}else{
//
//    	long tempThis;
//
//    	long tempB;
//
//    	for(int i=s1.length()-1; i>-1; i--){
//
//    		tempThis = this.get(i);
//
//    		tempB = b.get(i);
//
//    		if(tempThis > tempB){
//
//    			if(this.getSign())
//
//    				return -1;
//
//    			else
//
//    				return 1;
//
//    		}
//
//    		else if(tempThis < tempB)
//
//    			if(this.getSign())
//
//    				return 1;
//
//    			else
//
//    				return -1;
//
//    		else
//
//    			continue;
//
//    		}
//
//    	}
//
//    	return 0;
//
//    	}
