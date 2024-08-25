using System;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int answer = 0;

            int N = Int16.Parse(sr.ReadLine());

            int[] arr = new int[N];

            for (int i = 0; i < N; i++)
            {
                arr[i] = Int32.Parse(sr.ReadLine());
            }

            for (int i = arr.Length - 2; i >= 0; i--)
            {
                if (arr[i] >= arr[i + 1])
                {
                    int diff = Math.Min(arr[i] - arr[i + 1] + 1, arr[i] - 1);
                    answer += diff;
                    arr[i] -= diff;
                }
            }
           
            Console.Write(answer);

            sw.Close();
            sr.Close();
        }
    }
}

