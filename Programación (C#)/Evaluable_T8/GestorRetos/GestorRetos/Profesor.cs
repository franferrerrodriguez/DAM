using System;
using System.Linq;

namespace GestorRetos
{
    public class Profesor : Usuario
    {
        private string lenguaje_prog_preferido;

        public Profesor(string nickname, string email, string lenguaje_prog_preferido) : base(nickname, email)
        {
            this.lenguaje_prog_preferido = lenguaje_prog_preferido;
        }

        public string GetLenguajeProgPreferido()
        {
            return lenguaje_prog_preferido;
        }

        public void SetLenguajeProgPreferido(string lenguaje_prog_preferido)
        {
            this.lenguaje_prog_preferido = lenguaje_prog_preferido;
        }

        public override void Imprimir()
        {
            Console.WriteLine(string.Format("\nProfesor: {0}({1}) Lenguaje: {2}\nRetos:", GetNickName(), GetEmail(), GetLenguajeProgPreferido()));

            if (GetRetos().Count() > 0)
                foreach (Reto reto in GetRetos())
                    reto.Imprimir();
            else
                Console.WriteLine("No hay retos resueltos");
        }
    }
}
