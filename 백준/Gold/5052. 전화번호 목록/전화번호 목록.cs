using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace ConsoleApp1
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            StreamReader sr = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter sw = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int T = Int32.Parse(sr.ReadLine());

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < T; i++)
            {
                int N = Int32.Parse(sr.ReadLine());

                List<string> list = new List<string>();
                for (int j = 0; j < N; j++)
                {
                    string curPhoneNumber = sr.ReadLine();
                    list.Add(curPhoneNumber);
                }

                list.Sort((a, b) => a.Length - b.Length);

                HashSet<string> set = new HashSet<string>(list);

                bool check = true;
                foreach(var num in list)
                {
                    if (!check) break;

                    string strNum = num.ToString();

                    for (int j = 1; j < strNum.Length; j++)
                    {
                        if (set.Contains(strNum.Substring(0, j)))
                        {
                            check = false;
                            break;
                        }
                    }
                }

                if (check)
                {
                    answer.Append("YES\n");
                    continue;
                }
                answer.Append("NO\n");
            }

            sw.Write(answer.ToString());
            
            sw.Close();
            sr.Close();
        }
    }
}
