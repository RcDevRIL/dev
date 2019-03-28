namespace LedManager
{
    internal class MyMessage
    {
        public static string Login = "\"r.chevallier\"";
        public static int NumLeds = 256; 
        private MyColor[] mColors;
        public MyMessage()
        {
            mColors = new MyColor[NumLeds];
           /*  for(byte i = 0; i<NumLeds ; i++){ // int i fait la même chose
                MyColor c = new MyColor();
                mColors[i] = c;
            } */
            int index = 0;
            for (byte i = 0; i < 16; i++)
            {
                for (byte j = 0; j < 16; j++)
                {
                    MyColor color = new MyColor();                    
                    mColors[index++] = color.PickColor("D:/dev/csharp/LedManager/resources/minerai1.png", j, i);
                }
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