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

            string[] input = sr.ReadLine().Split(' ');

            int N = Int32.Parse(input[0]);
            int M = Int32.Parse(input[1]);

            input = sr.ReadLine().Split(' ');

            Queue <int> peopleWhoKnowTruth = new Queue<int>();

            for (int i = 1; i < input.Length; i++)
            {
                peopleWhoKnowTruth.Enqueue(Int32.Parse(input[i]));
            }

            List<List<int>> party = new List<List<int>>();
            List<int>[] people = new List<int>[N + 1];
            for (int i = 1; i < people.Length; i++)
            {
                people[i] = new List<int>();
            }

            for (int t = 0; t < M; t++) 
            {
                input = sr.ReadLine().Split(' ');
                List<int> newParty = new List<int>();

                for (int i = 1; i < input.Length; i++)
                {
                    int cur = Int32.Parse(input[i]);
                    newParty.Add(cur);

                    people[cur].Add(party.Count + 1);
                }

                party.Add(newParty);
            }

            bool[] visited = new bool[N + 1];
            bool[] partyCheck = new bool[M + 1];

            while (peopleWhoKnowTruth.Any())
            {
                int cur = peopleWhoKnowTruth.Dequeue();

                foreach (var partyNum in people[cur])
                {
                    partyCheck[partyNum] = true;

                    foreach(var nextPerson in party[partyNum - 1])
                    {
                        if (visited[nextPerson]) continue;

                        peopleWhoKnowTruth.Enqueue(nextPerson);
                        visited[nextPerson] = true;
                    }
                }
            }
            
            for (int i = 1; i < partyCheck.Length; i++)
            {
                if (!partyCheck[i])
                {
                    answer++;
                }
            }

            Console.Write(answer);

            sw.Close();
            sr.Close();
        }
    }
}

