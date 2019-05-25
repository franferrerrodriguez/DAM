using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase Metaloide heredada de la clase Elemento, será uno de los subtipos esta clase y tendrá sus atributos no comunes
    /// </summary>
    class Metaloide : Elemento
    {
        private bool esMetalRefractario;
        private bool esMetalNoble;

        public Metaloide(string nombre, string simbolo_quimico, int numero_atomico, DateTime ano_descubrimiento, bool elemento_antiguo, bool esMetalRefractario, bool esMetalNoble) : base(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo)
        {
            this.esMetalRefractario = esMetalRefractario;
            this.esMetalNoble = esMetalNoble;
        }

        public bool EsMetalRefractario()
        {
            return esMetalRefractario;
        }

        public void SetMetalRefractario(bool esMetalRefractario)
        {
            this.esMetalRefractario = esMetalRefractario;
        }

        public bool EsMetalNoble()
        {
            return esMetalNoble;
        }

        public void SetMetalNoble(bool esMetalNoble)
        {
            this.esMetalNoble = esMetalNoble;
        }

        public override String ToString()
        {
            return string.Format("Refractario: {0} - Noble: {1}", EsMetalRefractario() ? "Sí" : "No", EsMetalNoble() ? "Sí" : "No");
        }
        public override String ToCsv()
        {
            return string.Format("{0};{1};{2}", base.ToCsv(), EsMetalRefractario() ? "si" : "no", EsMetalNoble() ? "noble" : "no noble");
        }

    }
}