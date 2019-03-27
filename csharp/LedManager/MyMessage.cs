using System;

namespace LedManager
{
    internal class MyMessage
    {
        public static string Login = "\"r.chevallier\"";
        public static byte NumLeds = 100; 
        private MyColor[] mColors;
        public MyMessage()
        {
            mColors = new MyColor[NumLeds];
            for(byte i = 0; i<NumLeds ; i++){ // int i fait la même chose
                MyColor c = new MyColor();
                mColors[i] = c;
            }
        }
        public MyMessage(MyColor[] colors) => this.mColors = colors;
        public string GetPayLoad()
        {
            string pL = JsonBuilder.Build(Login, mColors);
            return pL;
        }
    }
}