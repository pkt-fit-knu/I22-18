
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
class Node {
	String value;
	Node right;
	Node left;
	Node parent;

}
class Oper {
	Node root = new Node();
	
	void print() {
		walk(root);
	}

	void walk(Node root) {

		
			if (root.left != null) {
				walk(root.left);
			}
			System.out.print(root.value + ", ");
			if (root.right != null) {
				walk(root.right);
			}
		
	}
	
	void R1(String Value)
	{
		R(Value,root);
	}

	private Node R(String data, Node root)
	{
		if (root.right == null) {
			root.right = new Node();
			root.right.value = data;
			root.right.parent = root;
		} else
			root.right = ADDRIGHT(data, root.right);
		return root;
	}
	
	void addRigth(String Value)
	{
		ADDRIGHT(Value,root);
	}
	
	private Node ADDRIGHT(String data,Node root)
	{
		while(root.right!=null){
			root=root.right;
		}
		while(root.value!="KeyWord" )
				{
					root=root.parent;
				}
				
				if (root.right == null) {
					root.right = new Node();
					root.right.value = data;
					root.right.parent = root;
				} else
				{
					root=root.right;
					if (root.right == null) {
						root.right = new Node();
						root.right.value = data;
						root.right.parent = root;
					}
					else
					{
						root=root.right;
						if (root.right == null) {
							root.right = new Node();
							root.right.value = data;
							root.right.parent = root;
						}
					}
				}
				
			
			return root;
		
	}
	
	void L1(String Value)
	{
		L(Value,root);
	}
	
	private Node L(String data, Node root){
		if (root.left == null) {
			root.left = new Node();
			root.left.value = data;
			root.left.parent = root;
		} else
			{root.left = L(data, root.left);}
	return root;
	}
	void addLeft(String Value)
	{
		ADDLEFT(Value,root);
	}
	
	private Node ADDLEFT(String data,Node root)
	{	
		
		while(root.value!="End"){
			root=root.left;
		}
		
		
			if(root.left==null && root.value=="End")
			{
				while(root.value!="KeyWord" )
				{
					root=root.parent;
				}
				while(root.right!=null)
				{
					root=root.right;
				}
				while(root==null)
				{
					root=root.parent;
				}
				
				
				if (root.left == null) {
					root.left = new Node();
					root.left.value = data;
					root.left.parent = root;
				} else
				{
					root=root.left;
					if (root.left == null) {
						root.left = new Node();
						root.left.value = data;
						root.left.parent = root;
					}
					else
					{
						root=root.left;
						if (root.left == null) {
							root.left = new Node();
							root.left.value = data;
							root.left.parent = root;
						}
					}
				}
				
			}
			return root;
		
		
		
	}
	void add(String Value) {
		insert(Value, root);
	}
	
	private Node insert(String data,Node root)
	{
		if(root.value==null)
		{
			root.value=data;
			return root;
		}
		
		return root;
	}
}


public class treeDM {
	public static void main(String[] args)
    {
        
		Oper b=new Oper();
		
        String[] mas;
        String[][] data;
        int kil = 0;
        int count = 0;
        try{
        File file=new File("test.txt");
        FileReader fr=new FileReader(file);
        BufferedReader reader=new BufferedReader(fr);
        String line = reader.readLine();
        while(line !=null)
        {
        	mas = line.split(",");
            count = mas.length;//кількість стовпців
            kil++;//кількість рядків
        	System.out.println(line);
        	line=reader.readLine();
        }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
                
        data = new String[kil] [count];//великий вхідний масив даних
        kil=0;

       
        try{
        File file1=new File("test.txt");
        FileReader fr1=new FileReader(file1);
        BufferedReader reader1=new BufferedReader(fr1);
        String line1 = reader1.readLine();
        
        while (line1 != null)
        {
            for (int i = 0; i < count; i++)
            {
                mas = line1.split(",");
                data[kil] [i] = mas[i];
            }
            
            kil++;//кількість рядків
            line1=reader1.readLine();
        }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String [] result=new String[kil];
        for(int i=0;i<result.length;i++)
        {
        	result[i]=data[i][count-1];
        }
        
        
        
        int nOfClasses=2;//користувач уводить кількість класів
        String []namesOfClasses=new String[nOfClasses];
        int []taxIncome=new int[count];
        int [] stringIncome=new int[count];
        namesOfClasses[0]="No";
        namesOfClasses[1]="Yes";//користувач уводить самостійно
        taxIncome[0]=3;
        stringIncome[0]=1;
        stringIncome[1]=2;
        
        double [] allJinnies=new double[count-1];
        
        int n=0;
        while(stringIncome[n]!=0)
        {
        	int temp=stringIncome[n];
        	allJinnies[temp-1]=preparationForJinni(data,kil,count,n,namesOfClasses);
        	n++;
        }
        n=0;
        while(taxIncome[n]!=0){
        	int temp=taxIncome[n];
        	 double [] taxableMas=findTaxableIncomeSort(data,temp-1,kil);
             double [] taxableFinalMas=taxableIncome(taxableMas);
             double [] jTaxable=new double[taxableFinalMas.length];
             
             for(int i=0;i<jTaxable.length;i++)
             {
             	jTaxable[i]=jinniTaxable(taxableFinalMas[i],namesOfClasses,taxableMas,result);
             }
             
             double min=findMinPositiveValue(jTaxable);
             double mainValue=0;
             for(int i=0;i<jTaxable.length;i++)
             {
            	 if(min==jTaxable[i])
            		 mainValue=taxableFinalMas[i];
             }
             n++;
             
             double [] mass=new double[result.length];
             for(int i=0;i<result.length;i++)
             {
            	 mass[i]=Double.parseDouble(data[i][temp-1]);
             }
             double jSplitTax=jinniTaxable(mainValue,namesOfClasses,mass,result);
             allJinnies[temp-1]=jSplitTax;
             
             
             
             
            
           //  String [][] masForJinni=countOfClasses();
        }  
        
        
        
        
        
        int counter=0;
        int fl=0;
        String KeyWord="KeyWord";
        b.add(KeyWord);
        while(counter<allJinnies.length || fl==0){
	        double minJinni=findMinPositiveValue(allJinnies);
	        int coolColumn=0;
	        for(int i=0;i<allJinnies.length;i++)
	        {
	        	if (minJinni==allJinnies[i])
	        	{
	        		coolColumn=i;
	        		allJinnies[i]=-1;
	        	}
	        }
	        String[]values=new String[4];
	        for(int i=0;i<taxIncome.length;i++)
	        {
	        	if(counter==taxIncome[i]-1)
	        	{
	        		values=findValueForTreeTax(2,data,kil,count,namesOfClasses);
	        		break;
	        	}	        
	     
	        else {
	        values=findValueForTree(coolColumn,data,kil,count, namesOfClasses);}
	        }
	        
	        if(counter==0)
	        {
	        	b.L1(values[0]);
	        	b.L1(values[1]);
	        	b.L1("End");
	        	b.R1(values[2]);
	        	if(values[3]==null)
	        	{
	        		b.R1("KeyWord");
	        	}
	        }
	        	
	        else{
	        	b.addLeft(values[0]);
	        	b.addLeft(values[1]);
	        	b.addLeft("End");
	        	b.addRigth(values[2]);
	        	if(values[3]==null)
	        	{
	        		b.addRigth("KeyWord");
	        	}
	        		
	        }	
	        
	        if(values[3]!=null && values[3]!="KeyWord")
	        {
	        	b.addRigth(values[3]);
	        	b.addRigth("End");
	        	fl=1;
	        }
	        counter++;
        }
        System.out.println("");
        b.print();
        }
	
	
	static String[] findValueForTreeTax(int column, String [][] data, int length, int rows, String [] namesOfClasses){
		double [] currentMas=new double[length];
		String [] result = new String[length];
		String [] fin=new String[4];
		for(int i=0;i<result.length;i++)
		{
			currentMas[i]=Double.parseDouble(data[i][column]);
			result[i]=data[i][rows-1];
		}
		
		double [] masiv=findTaxableIncomeSort(data,column,result.length);
		double [] taxableMas=taxableIncome(masiv);
		double [] jinni=new double[taxableMas.length];
		for(int i=0;i<taxableMas.length;i++)
		{
		double j=jinniTaxable(taxableMas[i],namesOfClasses,masiv,result);
		jinni[i]=j;
		}
		
		double minValue=findMinPositiveValue(jinni);
		double mainValue=0;
		for(int i=0;i<jinni.length;i++)
		{
			if(minValue==jinni[i])
				mainValue=taxableMas[i];
		}
		fin[0]="<="+mainValue;
		fin[2]=">"+mainValue;
		int [][]mas=new int[namesOfClasses.length][2];
		for(int i=0;i<namesOfClasses.length;i++)
		{
			for(int j=0;j<result.length;j++)
			{
					if(currentMas[j]<=mainValue && result[j].equals(namesOfClasses[i]))
					{
						mas[i][0]++;
					}
					else if(currentMas[j]>mainValue && result[j].equals(namesOfClasses[i]))
					{
						mas[i][1]++;					
				}
				
			}
		}
		double [] mas1=new double[2];
		mas1[0]=mas[0][0];
		mas1[1]=mas[1][0];
		
		double [] mas2=new double[2];
		mas2[0]=mas[0][1];
		mas2[1]=mas[1][1];
		
		double min1=findMinPositiveValue(mas1);
		double min2=findMinPositiveValue(mas2);
		
		for(int i=0;i<mas1.length;i++)
		{
			if(min1==mas1[i])
				fin[1]=namesOfClasses[1-i];
			if(min2==mas2[i])
				fin[3]=namesOfClasses[1-i];
		}
		
		//System.out.println("GF");
		return fin;
	}
	static String[] findValueForTree(int column, String [][]data, int length, int rows,String [] namesOfClasses){
		String [] currentMas=new String[length];
		String [] result = new String[length];
		String [] fin=new String[4];
		for(int i=0;i<result.length;i++)
		{
			currentMas[i]=data[i][column];
			result[i]=data[i][rows-1];
		}
		
		String [][]mas=countOfClasses(result,namesOfClasses,currentMas);
		int count=mas.length/namesOfClasses.length;
		double []jinni=Jinni(mas,count,namesOfClasses.length);
		int n=0;
		for(int i=0;i<jinni.length;i++)
		{
			if(jinni[i]==0)
			{
				fin[n]=mas[2*i][0];
				n++;
				int t=0;
				for(int k=2*i;k<2*i;k++)
				{
					if(Double.parseDouble(mas[k][2])==0)
						t=k;
						
				}
				fin[n]=mas[t][1];
				n++;
				for(int j=0;j<jinni.length;j++)
				{
					if(j!=i)
					{
						if(fin[n]==null)
							{fin[n]=mas[2*j][0];}
						else
						{fin[n]+=" "+mas[2*j][0];}
						
					}					
				}
			}
			
		}
		return fin;
	}
	static double findMinPositiveValue(double [] mas)
	{
		double min=0;
		int pos=0;
		for(int i=0;i<mas.length;i++)
		{
			if(mas[i]>=0)
				{	
				min=mas[i];
				pos=i;
				break;
				}
		}
		for(int i=pos;i<mas.length;i++)
		{
			if(mas[i]<min && mas[i]>=0)
				min=mas[i];
		}
		return min;
	}

 //закінчення Main
	static double preparationForJinni(String [][]data, int allStrings, int allColumns,int numberOfColumn, String [] namesOfClasses)
	{//головний по Джинні
		String [] currentData=new String[allStrings];
		String[] classData=new String[allStrings];
		String[][]masForJinni;
		for(int i=0;i<allStrings;i++)
		{
			currentData[i]=data[i][numberOfColumn];
			classData[i]=data[i][allColumns-1];
						
		}
		masForJinni=countOfClasses(classData,namesOfClasses,currentData);
		//System.out.println(masForJinni.length);
		int count=masForJinni.length/namesOfClasses.length;
		double [] jinni;
		jinni=Jinni(masForJinni,count,namesOfClasses.length);
		double jinniSplit=0;
		jinniSplit=jinniSplit(jinni,classData,count,namesOfClasses.length,masForJinni);
		
		//System.out.println("Hello");
		return jinniSplit;
	}
	//закінчення методу Jinni
	
	
	    static String[][] countOfClasses(String []classData,String []namesOfClasses,String []currentData){
		String []mas1=namesOfClasses;
		Integer []mas2=new Integer[namesOfClasses.length];
		
		String [] namesOfAttributes=new String[classData.length];
		
		for(int i=0;i<namesOfClasses.length;i++)
		{
			mas2[i]=0;
		}
		int fl=1;
		namesOfAttributes[0]=currentData[0];
		int h=0, pos=0;
		
		for(int i=1;i<namesOfAttributes.length;i++)//рахуються назви атрибутів
		{
			for(int j=0;j<namesOfAttributes.length;j++)
			{
				if(currentData[i].equals(namesOfAttributes[j]))
				{
					fl=0;					 
				}
			}
			if(fl==1)
			{
				pos++;
				namesOfAttributes[pos]=currentData[i];
			}
			fl=1;
		}//рахуються назви атрибутів
		
		int count=0;
		int m=0;
		while(namesOfAttributes[m]!=null)
		{
			count++;
			m++;
		}
		
		Integer [] thirdMas=new Integer[count*mas1.length];
		int b=0;
		
		for(int k=0;k<count;k++){//рахується кількість певного класу
		for(int i=0;i<classData.length;i++)
		{
			for(int j=0;j<namesOfClasses.length;j++)
			{
				if((mas1[j].equals(classData[i])) & (currentData[i].equals(namesOfAttributes[k])) )				
					{mas2[j]++;}
			}
			
		}
		for(int v=0;v<mas2.length;v++){
			thirdMas[b]=mas2[v];
			mas2[v]=0;
			b++;
			}
		
		}//рахується кількість певного класу
		
		String [][]result=new String[count*mas1.length][3];
		int calc=0;
		int f=1;
		for(int k=0;k<3;k++){
		for(int i=0;i<count;i++)
		{
			for(int j=0;j<mas1.length;j++)
			{
				if(k==0){
				result[calc][k]=namesOfAttributes[i];
				calc++;
				}
				if(k==1 & f==1)
					{f=0;calc=0;}
				if(k==1 & f==0)
				{
					result[calc][k]=mas1[j];
					calc++;
				}
				if(k==2)
				{
					calc=0;
					while(calc!=count*mas1.length)
					{
						result[calc][2]=thirdMas[calc].toString();		
						calc++;
					}
					break;
				}
			}
		}
		}
		
		return result;
	}
	//закінчення методу countOfClasses
	
	static double [] Jinni(String [][]mas, int count,int numberOfClasses){
		double [] jinni=new double[count];
		double [] promCalc=new double[count];//проміжні розрахунки
		double [] allSuma=new double[count];
		int jj=0;
		for(int i=0;i<count;i++)
		{
		
				for(int j=0;j<numberOfClasses;j++)
				{
					allSuma[i]+=Double.parseDouble(mas[jj][2]);//загальна кількість екземплярів класу в атрибуті
					jj++;
				}

		}
		jj=0;
		for(int i=0;i<count;i++)
		{
		
				for(int j=0;j<numberOfClasses;j++)
				{
					double temp=Double.parseDouble(mas[jj][2]);
					promCalc[i] += Math.pow(temp/allSuma[i], 2);
					jj++;
				}
			jinni[i]=1-promCalc[i];
		}
		
		
		return jinni;
	}
	//кінець методу
	
	static double jinniSplit(double [] jinni, String []classData,int count,int numberOfClasses,String [][]masForJinni){
		double jSplit=0;
		int jj=0;
		double [] littleCalc=new double[count];
		for(int i=0;i<count;i++)
		{		
				for(int j=0;j<numberOfClasses;j++)
				{
					littleCalc[i]+=Double.parseDouble(masForJinni[jj][2]);//загальна кількість екземплярів класу в атрибуті
					jj++;
				}

		}
		for(int i=0;i<count;i++)
		{
			jSplit+=littleCalc[i]/classData.length*jinni[i];
			
		}
		return jSplit;
	}
	
	static double jinniTaxable(double elementForComparison, String [] namesOfClasses,double []taxableMas,String[]result){
		int[][]mas=new int[namesOfClasses.length][2];
		//000000000000000
		for(int i=0;i<namesOfClasses.length;i++)
		{
			for(int j=0;j<2;j++)
			{
				mas[i][j]=0;
			}
		}	
		//000000000000000
		for(int i=0;i<namesOfClasses.length;i++)
		{
			for(int j=0;j<result.length;j++)
			{
					if(taxableMas[j]<=elementForComparison && result[j].equals(namesOfClasses[i]))
					{
						mas[i][0]++;
					}
					else if(taxableMas[j]>elementForComparison && result[j].equals(namesOfClasses[i]))
					{
						mas[i][1]++;					
				}
				
			}
		}
		
		//{	
		
		
			double []suma=new double[2];
			double []sumaWithoutZeros=new double[2];
			for(int i=0;i<2;i++)
			{
				for(int j=0;j<namesOfClasses.length;j++)
				{
					suma[i]+=mas[j][i];
					sumaWithoutZeros[i]+=mas[j][i];
				}
			}
			
			for(int i=0;i<suma.length;i++)
			{
				if(sumaWithoutZeros[i]==0)
				{
					sumaWithoutZeros[i]++;
				}
			}
			
		double[]jj=new double[2];
			for(int i=0;i<2;i++)
			{ double temp=0;
				for(int j=0;j<namesOfClasses.length;j++)
				{
					double t=mas[j][i]/sumaWithoutZeros[i];
					temp+=Math.pow(t, 2);
				}
				jj[i]=1-temp;
			}
			
			double jSplit=0;
			for(int i=0;i<2;i++)
			{
				jSplit+=suma[i]/result.length*jj[i];
			}
		//}   Маленьке ДжинніСпліт для вхідних числових диних
		//System.out.println("Hello");
		return jSplit;
	
	}
	
	static double [] taxableIncome(double [] data){//знаходимо середину між відсортованими числами
		double [] mas =new double[data.length+1];
		mas[0]=data[0]-1;
		for(int i=1;i<data.length+1;i++)
		{
			if(i==data.length)
			{
				mas[i]=data[i-1]+1;
			}
			else{
			mas[i]=(data[i-1]+data[i])/2;
			}
			
		}
		return mas;
	}
	
	static double [] findTaxableIncomeSort(String [][]mas, int numberForTaxable, int kil){
		double [] temp=new double[kil];
		//double [] mas1 = new double[kil];
		for(int i=0;i<kil;i++)
		{
			temp[i]=Double.parseDouble(mas[i][numberForTaxable]);
		}
		
		for (int i = 0; i < temp.length - 1; i++) {
	        for (int j = 0; j < temp.length - i - 1; j++) {
	            if (temp[j] > temp[j + 1]) {
	                double tmp = temp[j];
	                temp[j] = temp[j + 1];
	                temp[j + 1] = tmp;
	            }
	        } 
		}
		
		return temp;
	}
	}
	



