using System;
using System.IO;
using System.Linq;
using System.Text;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int T = Int16.Parse(sr.ReadLine());

            StringBuilder answer = new StringBuilder();
            for (int i = 1; i <= T; i++)
            {
                string word = String.Join(" ", sr.ReadLine().Split(' ').Reverse());
                answer.Append($"Case #{i}: {word}\n");
            }

            sw.Write(answer);

            sw.Close();
            sr.Close();
        }
    }
}

