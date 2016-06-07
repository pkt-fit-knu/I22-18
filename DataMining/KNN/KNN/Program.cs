using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KNN
{
    class Program
    {
        static void Main(string[] args)
        {
            double[] similarities = new double[2];
            int count = 0;
            for (int i = 1; i < 6; i++)
            {
                count += GetData(i).Length;
            }
            int k = 0;
            String[] data = new String[count];
            for (int i = 1; i < 6; i++)
            {
                String[]temp= GetData(i);
                for (int j = 0; j < temp.Length; j++)
                {
                    data[k] = temp[j];
                    k++;
                }
            }

            string []treningData=GetTrenningData(data);
            


            //Sport articles
            int count1 = 0;
            for (int i = 6; i < 11; i++)
            {
                count1 += GetData(i).Length;
            }
            k = 0;
            String[] data1 = new String[count1];
            for (int i = 6; i < 11; i++)
            {
                String[] temp1 = GetData(i);
                for (int j = 0; j < temp1.Length; j++)
                {
                    data1[k] = temp1[j];
                    k++;
                }
            }
            string[] treningData1 = GetTrenningData(data1);

            for (int i = 0; i < treningData.Length; i++)
            {
                for (int j = 0; j < treningData1.Length; j++)
                {
                    if (treningData[i] == treningData1[j] && treningData[i]!=null)
                    {
                        treningData[i] = null;
                        treningData1[j] = null;
                    }
                }
            }

            int treningCount = 0;
            int treningCount1 = 0;
            for (int i = 0; i < treningData.Length;i++)
            {
                if (treningData[i] == null)
                {
                    treningCount++;
                }
            }
            string[] trenData = new string[treningData.Length - treningCount];
            string[] trenData1 = new string[treningData1.Length - treningCount];
            int calc=0;
            int calc1 = 0;
            for (int i = 0; i < treningData.Length; i++)
            {
                if (treningData[i] != null)
                { trenData[calc] = treningData[i]; calc++; }
                if (treningData1[i] != null)
                { trenData1[calc1] = treningData1[i]; calc1++; }

            }

            similarities[0] = Similarity(trenData);
            similarities[1] = Similarity(trenData1);

            if (similarities[0] > similarities[1])
                Console.WriteLine("Medical article");
            else
                Console.WriteLine("Sport article");
            Console.ReadKey();
        }

        static double Similarity(string[] treningData)
        {
            double M00=0;
            double M11=0;
            double M01 = 0;
            double M10=0;
            
            string [] inputData=GetData(11); //choose data
            int[] wordVector = new int[inputData.Length];
            for (int i = 0; i < inputData.Length; i++)
            {
                for (int j = 0; j < treningData.Length; j++)
                {
                    if (inputData[i] == treningData[j])
                    {
                        wordVector[i] = 1;
                        break;
                    }
                }
            }

            for (int i = 0; i < wordVector.Length; i++)
            {
                if (wordVector[i] !=1)
                {
                    wordVector[i] = 0;
                }
            }
            int[] ones = new int[wordVector.Length];
            for (int i = 0; i < ones.Length; i++)
            {
                ones[i] = 1;
            }
                for (int i = 0; i < wordVector.Length; i++)
                {
                    if (wordVector[i] == 0 && ones[i] == 0)
                        M00++;
                    else if (wordVector[i] == 1 && ones[i] == 1)
                        M11++;
                    else if (wordVector[i] == 0 && ones[i] == 1)
                        M01++;
                    else if (wordVector[i] == 1 && ones[i] == 0)
                        M10++;
                }

            double SMC = (M11 + M00) / (M11 + M00 + M10 + M01);
            return SMC;
        }

        static string[] GetTrenningData(string []mas)
        {
            int count = 0;
            int fl = 0;
            String[] temp = new string[mas.Length];
            for (int i = 0; i < mas.Length; i++)
            {
                for (int j = 0; j < mas.Length; j++)
                {
                    if (mas[i] == temp[j])
                    {
                        fl = 1;
                    }
                }
                if (fl == 0)
                {
                    temp[count] = mas[i];
                    count++;
                }
                fl = 0;
            }

            int[] data = new int[count];

            for (int i = 0; i < mas.Length; i++)
            {
                for (int j = 0; j < count; j++)
                {
                    if (mas[i] == temp[j])
                    {
                        data[j]++;
                        break;
                    }
                }
            }
            int m = 0;
            for (int i = 0; i < temp.Length; i++)
            {
                if (mas[i] == "основан")
                    m++;
            }

            //buble sort
            int tmp;
            string tmp1;
        for(int i=0; i < data.Length-1; i++){
 
            for(int j=1; j < data.Length-i; j++){
                if(data[j-1] < data[j]){
                    tmp=data[j-1];
                    data[j-1] = data[j];
                    data[j] = tmp;

                    tmp1 = temp[j - 1];
                    temp[j - 1] = temp[j];
                    temp[j] = tmp1;
                }
            }
            
        }
            //buble sort
        string[] treningData = new string[500];
        for (int i = 0; i < treningData.Length; i++)
            treningData[i] = temp[i];

        return treningData;
        }


        static string [] GetData(int number)
        {
            int counter = 0;
            string s="";
            string line;
            System.IO.StreamReader file =
                new System.IO.StreamReader(@""+number+".txt");
            while ((line = file.ReadLine()) != null)
            {
               // System.Console.WriteLine(line);
                s = line;
                counter++;
            }

            file.Close();
            string Stroka=s.ToLower();
            char[] mas = { ',', '.', '<', '>', '!', '?', ':', ';', '(', ')', '—', '_', '+', '=', '«', '»', '%', '-','˚'};
            int i = 0;
            StringBuilder strB = new StringBuilder(Stroka);
            while (i != Stroka.Length)
            {
                for (int j = 0; j < mas.Length; j++)
                {
                    if (Stroka[i] == mas[j])
                    {
                        strB[i] = ' ';
                        
                    }
                }
                    i++;
            }

            String ss = strB.ToString();
            i = 1;
            int count = 0;
            String str = ss;
            StringBuilder strB2=new StringBuilder(str);
            while (i != ss.Length)
            {
                if(ss[i]==ss[i-1] && ss[i]==' ')
                {
                    i++; 
                }
                else 
                {
                    count++;
                    i++;
                    strB2[count-1] = ss[i - 1];
                    
                }
                
            }
           // String [] dataMas = ss.Split(' ');
            String[] dataMas1 = strB2.ToString().Split(' ');
           
            count=0;
            for (int j = 0; j < dataMas1.Length; j++)
            {
                if (dataMas1[j].Length <= 3)
                {

                }
                else
                {
                    count++;
                }
            }
            String[] dataMas2 = new String[count];
            count = 0;
            for (int j = 0; j < dataMas1.Length; j++)
            {
                if (dataMas1[j].Length <= 3)
                {

                }
                else
                {
                    dataMas2[count] = dataMas1[j];
                    count++;
                }
            }
            Char[] ends = { 'а', 'я', 'о', 'у', 'е', 'ы', 'и', 'й', 'ь' };
            for (int j = 0; j < dataMas2.Length; j++)
            {
                int start = dataMas2[j].Length - 1;
                for (int k = 0; k < ends.Length; k++)
                {
                    if (dataMas2[j][start] == ends[k]&& start!=0)
                    {
                        String d=dataMas2[j].Substring(0,start);
                        dataMas2[j] = d;
                        start=dataMas2[j].Length-1;
                        for (int l = 0; l < ends.Length; l++)
                        {
                            if (dataMas2[j][start] == ends[l] && start != 0)
                            {
                                d = dataMas2[j].Substring(0, start);
                                dataMas2[j] = d;
                                break;
                            }
                        }
                            break;
                    }

                }
            }


            return dataMas2;
            
        }
    }
}