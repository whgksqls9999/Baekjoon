using System;
using System.Collections.Generic;
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
            StringBuilder answer = new StringBuilder();

            string word;
            while((word = sr.ReadLine()) != "end")
            {
                bool check = false;
                if (!word.HasContinousSameType() && !word.HasContinousSameChar() && word.HasVowel())
                {
                    check = true;
                }
                answer.Append($"<{word}> is {(check ? "" : "not ")}acceptable.\n");
            }

            sw.Write(answer);

            sw.Close();
            sr.Close();
        }


    }

    public static class Extension {
        public static HashSet<char> vowel = new HashSet<char>(new char[] { 'a', 'e', 'i', 'o', 'u' });
        public static bool HasVowel(this string str)
        {
            return vowel.Any((it) => str.Contains(it));
        }

        public static bool HasContinousSameType(this string str)
        {
            int vowelCnt = 0;
            int consonantCnt = 0;

            foreach (char c in str)
            {
                if (vowel.Contains(c))
                {
                    consonantCnt = 0;
                    vowelCnt++;
                }
                else
                {
                    consonantCnt++;
                    vowelCnt = 0;
                }

                if (vowelCnt == 3 || consonantCnt == 3) return true;
            }
            return false;
        }

        public static bool HasContinousSameChar(this string str)
        {
            char prev = str[0];
            for (int i = 1; i < str.Length; i++)
            {
                char cur = str[i];

                if (prev == cur && prev != 'e' && prev != 'o')
                {
                    return true;
                }

                prev = cur;
            }
            return false;
        }
    }

}

