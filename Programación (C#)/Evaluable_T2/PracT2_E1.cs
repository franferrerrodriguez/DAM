/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 2
Ejercicio 1 (PracT2_E1.cs)
*/

using System;

namespace PracT2_E1
{
    class PracT2_E1
    {
        static void Main(string[] args)
        {
            int n_prac_2, n_prac_4, n_exam;

            Console.Write("Introduce tu nota de la práctica 2: ");
            n_prac_2 = Convert.ToInt32(Console.ReadLine());

            Console.Write("Introduce tu nota de la práctica 4: ");
            n_prac_4 = Convert.ToInt32(Console.ReadLine());

            Console.Write("Introduce tu nota del examen: ");
            n_exam = Convert.ToInt32(Console.ReadLine());

            if(n_prac_2 >= 3 && n_prac_4 >= 3 && (n_prac_2 + n_prac_4 / 2) >= 4 && n_exam >= 4)
            {
                Console.WriteLine("Puedes aprobar la evaluación.");
            }
            else if ((n_prac_2 < 3 || n_prac_4 < 3 || (n_prac_2 + n_prac_4 / 2) < 4) && n_exam >= 4)
            {
                Console.WriteLine("Debes recuperar las prácticas.");
            }
            else if (n_prac_2 >= 3 && n_prac_4 >= 3 && (n_prac_2 + n_prac_4 / 2) >= 4 && n_exam < 4)
            {
                Console.WriteLine("Debes recuperar el examen.");
            }
            else
            {
                Console.WriteLine("Debes recuperarlo todo.");
            }
        }
    }
}
