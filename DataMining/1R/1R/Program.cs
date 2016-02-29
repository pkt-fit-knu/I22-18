using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _1R
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Insert the count of values in last column:");
            int n = Int32.Parse(Console.ReadLine());
            string[,] vid1 = new string[n, 2];
            Console.WriteLine("Insert names of values in the last column:");            
            for (int i = 0; i < n; i++)
            {
                vid1[i, 0] = "0";
               // vid[i, 1] = Console.ReadLine();
                vid1[0, 1] = "Iris-setosa";
                vid1[1, 1] = "Iris-versicolor";
                vid1[2, 1] = "Iris-virginica";
            }
            
            string line;
            string[] mas;
            string[,] data;
            int kil = 0;
            int count = 0;

            System.IO.StreamReader file = new System.IO.StreamReader("iris.txt");
            while ((line = file.ReadLine()) != null)
            {
                    mas = line.Split(',');
                    count = mas.Length;
                    kil++;
            }
            data = new string[kil, count];
            kil=0;

            System.IO.StreamReader file1 = new System.IO.StreamReader("iris.txt");
            while ((line = file1.ReadLine()) != null)
            {
                for (int i = 0; i < count; i++)
                {
                    mas = line.Split(',');
                    data[kil, i] = mas[i];
                }
               // Console.WriteLine("{0} {1} {2} {3}", data[kil, 0], data[kil, 1], data[kil, 2], data[kil, 3]);
                kil++;
            }

            file.Close();
            file1.Close();

            int error1 = 0;
            int[] errorMass = new int[count-1];
            for (int i = 0; i < count-1; i++)
            {
                errorMass[i]=Calculation(data, kil, count, vid1, error1,i);

            }
            int number=0;
            for (int i = 0; i < errorMass.Length; i++)
            {
                if (errorMass[i] == errorMass.Min())
                { number = i; break; }
            }

            Console.WriteLine("");
            Console.WriteLine("The most effective algorithm is {0}", number+1);
            Console.ReadKey();
            Console.WriteLine("There were {0} lines.", kil);
            Console.ReadLine();
        }



      static int Calculation(string [,] data,int rows,int cols,string [,] vid,int error, int numberOfColumn)
        {
          //вивід інформації про найбільш дієвий метод
            
   //             Console.WriteLine("It works");
                double[] arr = new double[rows];
                string[] answer = new string[rows];
                for (int i = 0; i < rows; i++)
                {
                    arr[i] = Double.Parse(data[i, numberOfColumn]);
                    answer[i] = data[i, cols-1];
                }

                for (int i = 0; i < arr.Length - 1; i++)
                {
                    for (int j = 0; j < arr.Length - i - 1; j++)
                    {
                        if (arr[j] > arr[j + 1])
                        {
                            double tmp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = tmp;

                            string tmp1 = answer[j];
                            answer[j] = answer[j + 1];
                            answer[j + 1] = tmp1;
                        }
                    }
                }

                

                //далі описується алгоритм розподілення значень на групки з урахуванням помилок
                //
                {
                    string[,] group = new string[answer.Length, 2];
                    int groupId = 0;
                    error = 0;

                    for (int i = 0; i < answer.Length; i++)
                    {

                        for (int j = 0; j < vid.Length / 2; j++)
                        {
                            if (answer[i] == vid[j, 1])
                            {
                                int n = Int32.Parse(vid[j, 0]);
                                n++;
                                vid[j, 0] = n.ToString();
                            }
                        }


                        for (int k = 0; k < vid.Length / 2; k++)//формування групи
                        {
                            if (Int32.Parse(vid[k, 0]) == 3)
                            {
                                for (int m = 0; m < vid.Length / 2; m++)
                                { error = error + Int32.Parse(vid[m, 0]); }
                                error = error - 3;
                                group[groupId, 1] = vid[k, 1];
                                group[groupId, 0] = (i - 1).ToString();

                                int count = i;
                                int extantion = 0;
                                while (answer[count] == vid[k, 1])
                                {
                                    extantion++;
                                    count++;
                                    if (count == 150) { break; }
                                }
                                group[groupId, 0] = (i + extantion - 1).ToString();
                                i = i + extantion - 1;
                                for (int j = 0; j < vid.Length / 2; j++)
                                    vid[j, 0] = "0";
                                groupId++;
                            }

                        }
                    }
                    Console.WriteLine("");
                    Console.WriteLine("");
                    Console.WriteLine("Number of column: {0}",numberOfColumn+1);
                    Console.WriteLine("Efficiency: {0}%", 100-((error * 100) / rows));
                    int fl = 0;
                    string down="";
                    int ii=1;

                    while (group[ii,0]!=null)
                    {
                        if (group[ii, 1] != group[ii - 1, 1])
                        {
                            if (fl == 0)
                            {
                                Console.WriteLine("if 0<X<={0} --> {1}", arr[Int32.Parse(group[ii-1, 0])], group[ii - 1, 1]);
                                fl = 1;
                                down = group[ii-1, 0];
                            }
                            else
                            {
                                Console.WriteLine("if {0}<X<={1} --> {2}", arr[Int32.Parse(down)], arr[Int32.Parse(group[ii - 1, 0])], group[ii - 1, 1]);
                                down = group[ii-1, 0];
                            }
                        }                        
                        ii++;
                        if (group[ii, 1] == null)
                        {
                            Console.WriteLine("if {0}<X -->{1}", arr[Int32.Parse(down)], group[ii - 1, 1]);
                        }
                    }
                    int mama;                                        
                }
                //
                return error;
            
          //end
        }

    }
}
