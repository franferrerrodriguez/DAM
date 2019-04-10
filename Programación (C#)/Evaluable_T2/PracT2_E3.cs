/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 2
Ejercicio 3 (PracT2_E3.cs)
*/

using System;

namespace PracT2_E3
{
    class PracT2_E3
    {
        static void Main(string[] args)
        {
            int n;

            Console.WriteLine("Introduce el lado de un hexágono regular: ");

            n = Convert.ToInt32(Console.ReadLine());
            int r = 2;

            for (int y = 0; y < 5; y++)
            {
                if (y > 0)
                {
                    if (y <= 5 / 2)
                        r--;
                    else
                        r++;
                }

                for (int x = 0; x < n + 4; x++)
                {
                    if (x >= r && x < (n + 4 - r))
                        Console.Write("*");
                    else
                        Console.Write(" ");
                }

                Console.WriteLine("");
            }
        }
    }
}
