using System;
using System.IO;
using System.Linq;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));

            string[] input = sr.ReadLine().Split(' ');
            int a = Rev(input[0]);
            int b = Rev(input[1]);
            int c = Rev((a + b).ToString());

            Console.WriteLine(c);
        }

        public static int Rev(string x)
        {
            char[] a = x.ToArray();
            Array.Reverse(a);
            return Int32.Parse(new string(a));
        }
    }
}
