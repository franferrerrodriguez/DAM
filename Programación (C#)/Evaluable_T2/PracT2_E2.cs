/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 2
Ejercicio 2 (PracT2_E2.cs)
*/

using System;

namespace PracT2_E2
{
    class PracT2_E2
    {
        static void Main(string[] args)
        {
            int questions = 10, nc = 0;
            String c;

            Console.WriteLine("Introduce las 10 correcciones (B o M):");

            for(int i = 0; i < questions; i++)
            {
                c = Console.ReadLine();

                // He preferico que compruebe ignorando case (B b) y (M m)
                if (
                     string.Equals(c, "B", StringComparison.OrdinalIgnoreCase) ||
                     string.Equals(c, "M", StringComparison.OrdinalIgnoreCase)
                    )
                {
                    if (string.Equals(c, "B", StringComparison.OrdinalIgnoreCase))
                        nc++;
                }
                else
                {
                    Console.WriteLine("Error, símbolo no válido.");
                    break;
                }

                // Results in last iteraction
                if(i.Equals(questions - 1))
                {
                    int ni = questions - nc;
                    int nr = ni / 3;
                    int nf = nc - nr;
                    Console.WriteLine(String.Format("Total acertadas: {0} ({0} puntos)", nc));
                    Console.WriteLine(String.Format("Total incorrectas: {0} (-{1} puntos)", ni, nr));
                    Console.WriteLine(String.Format("Nota final: {0}", nf));
                }
            }
        }
    }
}
