using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase NoMetal heredada de la clase Elemento, será uno de los subtipos esta clase y tendrá sus atributos no comunes
    /// </summary>
    class NoMetal : Elemento
    {
        private bool esGasNoble;

        public NoMetal(string nombre, string simbolo_quimico, int numero_atomico, DateTime ano_descubrimiento, bool elemento_antiguo, bool esGasNoble) : base(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo)
        {
            this.esGasNoble = esGasNoble;
        }

        public bool EsGasNoble()
        {
            return esGasNoble;
        }

        public void SetGasNoble(bool esGasNoble)
        {
            this.esGasNoble = esGasNoble;
        }

        public override String ToString()
        {
            return string.Format("Gas Noble: {0}", EsGasNoble() ? "Sí" : "No");
        }

        public override String ToCsv()
        {
            return string.Format("{0};{1}", base.ToCsv(), EsGasNoble() ? "gas noble" : "no noble");
        }

    }
}