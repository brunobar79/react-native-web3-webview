
Pod::Spec.new do |s|
  s.name         = "RNWeb3Webview"
  s.version      = "1.0.0"
  s.summary      = "RNWeb3Webview"
  s.description  = <<-DESC
                  RNWeb3Webview
                   DESC
  s.homepage     = ""
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "author" => "author@domain.cn" }
  s.platform     = :ios, "7.0"
  s.source       = { :git => "https://github.com/author/RNWeb3Webview.git", :tag => "master" }
  s.source_files  = "RNWeb3Webview/**/*.{h,m}"
  s.requires_arc = true


  s.dependency "React"
  #s.dependency "others"

end

  