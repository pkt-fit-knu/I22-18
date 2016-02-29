using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace iris
{
    class Program
    {
        static void Main(string[] args)
        {
            int counter = 0;
            string line;
            string [] mas=new string[4];
            string[] iris = new string[1];
            double a,b,c,d;

            System.IO.StreamReader file = new System.IO.StreamReader("test.txt");
            while ((line = file.ReadLine()) != null)
            {
                mas = line.Split(',');
                
                a=Double.Parse(mas[0]);
                b=Double.Parse(mas[1]);
                c=Double.Parse(mas[2]);
                d=Double.Parse(mas[3]);

                if (d < 1.0) { iris[0] = "Iris Setosa"; }
                else if (a >= 4.9 & a <= 7.0 & b >= 2.0 & b<=3.4 & c>=3.0 & c<=5.0 & d>=1.0 & d<=1.8) {iris[0]="Iris Versicolour";}
                else if (a >= 5.6 & a <= 7.9 & b >= 2.2 & b <= 3.8 & c >= 4.5 & c <= 6.9 & d >= 1.4 & d <= 2.5) { iris[0] = "Iris Virginica"; }
                Console.WriteLine("{0} {1} {2} {3} {4}", mas[0],mas[1],mas[2],mas[3],iris[0]);

                iris[0] = "";
                counter++;
            }

            file.Close();
            Console.ReadKey();
            Console.WriteLine("There were {0} lines.", counter);
            Console.ReadLine();
        }

    }
}
