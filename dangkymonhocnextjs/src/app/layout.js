import AppProvider from '@/components/MyUserReducer';
import './globals.css';
import 'bootstrap/dist/css/bootstrap.min.css';

export const metadata = {
  title: "Đăng ký môn học",
  description: "Welcome!",
};

export default function RootLayout({ children }) {

  return (
    <html lang="vi">
      <body>
        <AppProvider>{children}</AppProvider>
      </body>
    </html>
  );
}
