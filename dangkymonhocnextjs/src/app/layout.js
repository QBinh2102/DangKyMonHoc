import AppProvider from '@/components/MyUserReducer';
import './globals.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import AppLayoutWrapper from '@/components/AppLayoutWrapper';

export const metadata = {
  title: "Đăng ký môn học",
  description: "Welcome!",
};

export default function RootLayout({ children }) {

  return (
    <html lang="vi">
      <body style={{ margin: 0 }}>
        <AppProvider>
          <div style={{
            display: "flex",
            flexDirection: "column",
            minHeight: "100vh"
          }}>
            <AppLayoutWrapper>
              {children}
            </AppLayoutWrapper>
          </div>
        </AppProvider>
      </body>
    </html>
  );
}

