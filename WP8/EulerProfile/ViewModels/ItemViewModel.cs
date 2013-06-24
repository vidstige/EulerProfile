using System;
using System.ComponentModel;

namespace EulerProfile.ViewModels
{
    public class ItemViewModel : INotifyPropertyChanged
    {
        private string _id;
        private string _lineOne;
        private string _lineTwo;
        private string _lineThree;

        /// <summary>
        /// Sample ViewModel property; this property is used to identify the object.
        /// </summary>
        /// <returns></returns>
        public string ID
        {
            get { return _id; }
            set
            {
                _id = value;
                NotifyPropertyChanged("ID");
            }
        }

        public string LineOne
        {
            get { return _lineOne; }
            set
            {
                _lineOne = value;
                NotifyPropertyChanged("LineOne");
            }
        }

        public string LineTwo
        {
            get { return _lineTwo; }
            set
            {
                _lineTwo = value;
                NotifyPropertyChanged("LineTwo");
            }
        }

        public string LineThree
        {
            get { return _lineThree; }
            set
            {
                _lineThree = value;
                NotifyPropertyChanged("LineThree");
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;
        private void NotifyPropertyChanged(String propertyName)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (null != handler)
            {
                handler(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }
}