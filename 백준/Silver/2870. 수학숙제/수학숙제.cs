using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            StringBuilder answer = new StringBuilder();

            string regex = @"\d+";

            int T = Int32.Parse(sr.ReadLine());

            List<string> list = new List<string>();

            for (int i = 0; i < T; i++)
            {
                string input = sr.ReadLine();

                MatchCollection matches = Regex.Matches(input, regex);
                
                foreach (Match match in matches)
                {
                    string cur = match.Value;

                    int lastIndex = 0;
                    for (int j = 0; j < cur.Length; j++)
                    {   
                        if (cur[j] == '0')
                        {
                            lastIndex = j+1;
                        } else
                        {
                            break;
                        }
                    }

                    if (lastIndex == cur.Length)
                    {
                        lastIndex--;
                    }

                    list.Add(cur.Substring(lastIndex));


                }
            }
            list.Sort((a, b) =>
            {
                if (a.Length != b.Length)
                {
                    return a.Length - b.Length;
                }
                return a.CompareTo(b);
            });

            foreach (string i in list)
            {
                answer.Append($"{i}\n");
            }

            sw.Write(answer);

            sw.Close();
            sr.Close();
        }
    }
}

