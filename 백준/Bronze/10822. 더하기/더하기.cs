using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int answer = 0;
            
            int[] arr = sr.ReadLine().Split(',').Select((it) => Int32.Parse(it)).ToArray();
            answer = arr.Sum();

            sw.Write(answer);

            sw.Close();
            sr.Close();
        }
    }
}

