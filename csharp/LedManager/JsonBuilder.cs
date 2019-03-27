using System;

namespace LedManager
{
    internal class JsonBuilder
    {
        internal static string Build(string login, MyColor[] colors)
        {
            /* Json to parse :
            {
                "login" : "r.chevallier",
                "colors" : [
                    [255,255,255],
                ]
            }
            
             */
            string pL = string.Format("\"login\" : {0}, \"colors\" : [{1}]", login, BuildColorString(colors));
            pL = pL.Replace("],]","]]");
            return "{" + pL + "}";
        }
        private static string BuildColorString(MyColor[] colors)
        {
            string str = "";
            for(byte i=0; i<(colors.Length)-1; i++){
                str += String.Format("[{0},{1},{2}],", 
                    colors[i].getRValue(),
                    colors[i].getGValue(),
                    colors[i].getBValue()
                                    );
            }
            
            //recode last step to avoid last comma in our manual json representation
            /* str += String.Format("[{0},{1},{2}]", 
                    colors[(colors.Length)-1].getRValue(),
                    colors[(colors.Length)-1].getGValue(),
                    colors[(colors.Length)-1].getBValue()
                                    ); */
            return str;
        }
    }
}