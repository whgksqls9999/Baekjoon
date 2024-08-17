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

            int N = Int32.Parse(sr.ReadLine());

            int[,] arr = new int[N, N];
            for (int i = 0; i < N; i++)
            {
                int[] cur = sr.ReadLine().Split(' ').Select((it) => Int32.Parse(it)).ToArray();
                for (int j = 0; j < N; j++)
                {
                    arr[i, j] = cur[j];
                }
            }

            bool[,] visited = new bool[N, N];

            BFS(arr, visited, N);
            
            if (visited[N-1, N - 1])
            {
                sw.Write("HaruHaru");
            } else
            {
                sw.Write("Hing");
            }

            sw.Close();
            sr.Close();
        }

        public static void BFS(int[,] arr, bool[,] visited, int N)
        {
            Queue<int[]> queue = new Queue<int[]>();

            queue.Enqueue(new int[] { 0, 0 });
            visited[0, 0] = true;

            while (queue.Any())
            {
                int[] cur = queue.Dequeue();
               
                if (cur[0] == -1 && cur[1] == -1) break;

                for (int i = 0; i < 2; i++)
                {
                    int nr = cur[0] + (arr[cur[0], cur[1]]) * i;
                    int nc = cur[1] + (arr[cur[0], cur[1]]) * (1 - i);

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr, nc])
                    {
                        visited[nr, nc] = true;
                        queue.Enqueue(new int[] { nr, nc });
                    }
                }
            }

        }
    }
}

