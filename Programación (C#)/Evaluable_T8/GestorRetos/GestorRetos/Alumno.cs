using System;
using System.Linq;

namespace GestorRetos
{
    public class Alumno : Usuario
    {
        string curso;

        public Alumno(string nickname, string email, string curso) : base(nickname, email)
        {
            this.curso = curso;
        }

        public string GetCurso()
        {
            return curso;
        }

        public void SetCurso(string curso)
        {
            this.curso = curso;
        }

        public override void Imprimir()
        {
            Console.WriteLine(string.Format("\nAlumno: {0}({1}) Curso: {2}\nRetos:", GetNickName(), GetEmail(), GetCurso()));

            if(GetRetos().Count() > 0)
                foreach (Reto reto in GetRetos())
                    reto.Imprimir();
            else
                Console.WriteLine("No hay retos resueltos");
        }
    }
}