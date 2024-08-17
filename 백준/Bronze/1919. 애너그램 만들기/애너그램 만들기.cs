using System;
using System.Collections.Generic;
using System.IO;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            string A = sr.ReadLine();
            string B = sr.ReadLine();

            int answer = 0;

            Dictionary<char, int> dictA = new Dictionary<char, int>();
            Dictionary<char, int> dictB = new Dictionary<char, int>();

            SetDict(dictA, A);
            SetDict(dictB, B);

            foreach(char key in dictA.Keys)
            {
                int b;
                if (dictB.TryGetValue(key, out b))
                {
                    answer += Math.Abs(dictA[key] - b);
                } else
                {
                    answer += dictA[key];
                }
            }

            foreach(char key in dictB.Keys)
            {
                if (!dictA.ContainsKey(key))
                {
                    answer += dictB[key];
                }
            }

            sw.Write(answer);

            sw.Close();
            sr.Close();
        }

        public static void SetDict(Dictionary<char, int> dict, string str)
        {
            foreach (char c in str)
            {
                int value;
                if (dict.TryGetValue(c, out value))
                {
                    dict[c] = value + 1;
                    continue;
                }

                dict.Add(c, 1);
            }
        }
    }
}

