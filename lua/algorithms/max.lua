a={76,2,15,111}
max = a[1]
for i = 1, #a do
  if a[i]>max then
    max = a[i]
  end
end

print(max)