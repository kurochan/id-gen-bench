(1 .. 64).each do |num_thread|
  result = []
  open("result-#{num_thread}.log") {|file|
    result[0] = num_thread
    count = 0
    while line = file.gets
      count += 1
      next if count == 1
      record = line.split(" ")
      result[count - 1] = record[3]
    end
  }
  puts result.join(",")
end
