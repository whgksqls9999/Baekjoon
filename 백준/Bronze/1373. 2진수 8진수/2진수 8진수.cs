using System;
using System.Text;

namespace ConsoleApp1
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string N = Console.ReadLine();
            int length = (int) Math.Ceiling((float)N.Length / 3) * 3;

            N = N.PadLeft((int)length, '0');

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < N.Length; i += 3)
            {
                string str = N.Substring(i, 3);

                answer.Append(Convert.ToString(Convert.ToInt32(str, 2), 8));
            }

            Console.WriteLine(answer);
        }
    }
}
